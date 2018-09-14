import React from 'react';
import PropTypes from 'prop-types';
import { Form, Input, Modal} from "antd";
import { formItemLayout } from '../../constants/constants';
import { formColumn, userForm} from './index.css';

const FormItem = Form.Item;

const UserForm = ({
    user,
    type,
    disabled,
    onConfirm,
    onCancel,
    form: {
        getFieldDecorator,
        validateFieldsAndScroll
    }
}) => {
    let { id, loginName, mobile, name, identityCard } = user;
    const okButtonProps={
        disabled: disabled
    }
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
        <Modal
        visible={true}
        title="用户信息"
        okText="保存"
        onCancel={onCancel}
        cancelText='取消'
        onOk={handleSubmit}
        okButtonProps={okButtonProps}
      >
        <Form className={userForm} layout='horizontal' onSubmit={handleSubmit}>
            <span className={formColumn}>
                {/* <h2 className={formTitle}>用户信息</h2> */}
                {
                    type !== 'create'
                        ?
                        <FormItem label='账户id' {...formItemLayout}>
                            {
                                getFieldDecorator('id', {
                                    initialValue: id,

                                })(
                                    <Input type='text' disabled={true} />
                                )
                            }
                        </FormItem>
                        :
                        ""
                }
                <FormItem label='登录名' hasFeedback={!disabled} {...formItemLayout}>
                    {
                        getFieldDecorator('loginName', {
                            initialValue: loginName,
                            rules: [
                                { required: true, message: '用户名不能为空' }
                            ]
                        })(
                            <Input type='text' disabled={disabled} />
                        )
                    }
                </FormItem>
                <FormItem label='手机号' hasFeedback={!disabled} {...formItemLayout}>
                    {
                        getFieldDecorator('mobile', {
                            initialValue: mobile,
                            rules: [
                                { required: true, message: '手机号不能为空' }
                            ]
                        })(
                            <Input type='text' disabled={disabled} />
                        )
                    }
                </FormItem>
                <FormItem label='姓名' hasFeedback={!disabled} {...formItemLayout}>
                    {
                        getFieldDecorator('name', {
                            initialValue: name,
                            rules: [
                                { required: true, message: '姓名不能为空' }
                            ]
                        })(
                            <Input type='text' disabled={disabled} />
                        )
                    }
                </FormItem>
                <FormItem label='凭证号' hasFeedback={!disabled} {...formItemLayout}>
                    {
                        getFieldDecorator('identityCard', {
                            initialValue: identityCard,
                            rules: [
                                { required: true, message: '姓名不能为空' }
                            ]
                        })(
                            <Input type='text' disabled={disabled} />
                        )
                    }
                </FormItem>
            </span>
        </Form>
        </Modal>
    );
};

UserForm.prototypes = {
    form: PropTypes.object.isRequired,
    onSelect: PropTypes.func,
    onSubmit: PropTypes.func,
    user: PropTypes.any
}

export default Form.create()(UserForm);