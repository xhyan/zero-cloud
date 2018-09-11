import React from 'react';
import PropTypes from 'prop-types';
import { Form, Input } from "antd";
import { formItemLayout } from '../../constants/constants';
import { formColumn, formTitle, buttonGroup, confirmButton, cancelButton } from './index.css';
import { Button } from "antd";

const FormItem = Form.Item;

const UserForm = ({
    user,
    disable,
    onConfirm,
    onCancel,
    form: {
        getFieldDecorator,
        validateFieldsAndScroll
    }
}) => {
    let { loginName, name, identityCard } = user;

    const handleSubmit = (e) => {
        validateFieldsAndScroll((err, values) => {
            if (!!err) {
                return;
            }
            console.log(values);
            onConfirm(values);
        });
    }
    
    return (
        <Form layout='horizontal' onSubmit={handleSubmit}>
            <span className={formColumn}>
                <h2 className={formTitle}>用户信息</h2>
                <FormItem label='登录名' hasFeedback={!disable} {...formItemLayout}>
                    {
                        getFieldDecorator('loginName', {
                            initialValue: loginName,
                            rules: [
                                { required: true, message: '用户名不能为空' }
                            ]
                        })(
                            <Input type='text' disabled={disable} />
                        )
                    }
                </FormItem>
                <FormItem label='姓名' hasFeedback={!disable} {...formItemLayout}>
                    {
                        getFieldDecorator('name', {
                            initialValue: name,
                            rules: [
                                { required: true, message: '姓名不能为空' }
                            ]
                        })(
                            <Input type='text' disabled={disable} />
                        )
                    }
                </FormItem>
                <FormItem label='凭证号' hasFeedback={!disable} {...formItemLayout}>
                    {
                        getFieldDecorator('identityCard', {
                            initialValue: identityCard,
                            rules: [
                                { required: true, message: '姓名不能为空' }
                            ]
                        })(
                            <Input type='text' disabled={disable} />
                        )
                    }
                </FormItem>
                <FormItem>
                    <div className={buttonGroup}>
                        {
                            disable ?
                                null :
                                <Button type="primary" htmlType='submit' className={confirmButton}>确定</Button>
                        }
                        <Button type="ghost" className={cancelButton} onClick={onCancel}>取消</Button>
                    </div>
                </FormItem>
            </span>
        </Form>
    );
};

UserForm.prototypes = {
    form: PropTypes.object.isRequired,
    onSelect: PropTypes.func,
    onSubmit: PropTypes.func,
    user: PropTypes.any
}

export default Form.create()(UserForm);