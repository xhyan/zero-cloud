import React from 'react';
import PropTypes from 'prop-types';
import { Form, Input } from "antd";
import { formItemLayout } from '../../constants/constants';
import { userForm, formColumn, formTitle } from './index.css';

const FormItem = Form.Item;

const UserForm = ({
    user,
    disable,
    form: {
        getFieldDecorator
    }
}) => {
    let { loginName, name, identityCard } = user;

    return (
        <div className={userForm}>
            <Form>
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
                        } </FormItem>
                </span>
            </Form>
        </div>
    );
};

UserForm.prototypes = {
    form: PropTypes.object.isRequired,
    onSelect: PropTypes.func,
    user: PropTypes.any
}

export default Form.create()(UserForm);