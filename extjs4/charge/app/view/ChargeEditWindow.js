/*
 * File: app/view/ChargeEditWindow.js
 *
 * This file was generated by Sencha Architect version 2.1.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.1.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.1.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('MyApp.view.ChargeEditWindow', {
    extend: 'Ext.window.Window',

    border: 0,
    height: 250,
    width: 400,
    layout: {
        type: 'fit'
    },
    closable: false,
    closeAction: 'hide',
    title: '修改账单',
    modal: true,

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    autoRender: true,
                    draggable: true,
                    floating: false,
                    height: 250,
                    id: 'chargeEditForm',
                    width: 400,
                    bodyPadding: 10,
                    items: [
                        {
                            xtype: 'fieldset',
                            id: 'chargeEditFormFieldSet',
                            defaults: {
                                labelWidth: 50
                            },
                            title: '',
                            items: [
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: '金额'
                                },
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: '备注'
                                }
                            ]
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'bottom',
                            ui: 'footer',
                            items: [
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    id: 'chargeEditFormSaveBtn',
                                    text: '保存'
                                },
                                {
                                    xtype: 'button',
                                    id: 'chargeEditFormCancelBtn',
                                    text: '取消'
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});