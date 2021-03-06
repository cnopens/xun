/**
 * 
 */
package org.adaikiss.xun.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * @author hlw
 * 
 */
public class Server {

	private int port;
	private static final StringDecoder DECODER = new StringDecoder(
			CharsetUtil.UTF_8);
	private static final StringEncoder ENCODER = new StringEncoder(
			CharsetUtil.UTF_8);
	private static final ServerHandler SERVERHANDLER = new ServerHandler();

	private Server(int port) {
		this.port = port;
	}

	private void _start() throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							ChannelPipeline pipeline = ch.pipeline();

							// Add the text line codec combination first,
							pipeline.addLast("framer",
									new DelimiterBasedFrameDecoder(8192,
											Delimiters.lineDelimiter()));
							// the encoder and decoder are static as these are
							// sharable
							pipeline.addLast("decoder", DECODER);
							pipeline.addLast("encoder", ENCODER);

							// and then business logic.
							pipeline.addLast("handler", SERVERHANDLER);
						}

					});

			b.bind(port).sync().channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	public void start() throws Exception {
		new Thread(){
			@Override
			public void run(){
				try {
					_start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
		Scanner scanner = new Scanner(System.in);
		while(true){
			String s = scanner.nextLine();
			ServerHandler.sendToAll(s);
		}
	}

	public static void main(String[] args) throws Exception {
		new Server(12345).start();
	}
}
