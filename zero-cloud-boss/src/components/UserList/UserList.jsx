import React from 'react';
import PropTypes from 'prop-types';
import { Table, Pagination } from 'antd';
import { userList } from './index.css';

const UserList = ({
    loading,
    total,
    dataSource,
}) => {
    const columns = [
        {
            title: '序号',
            dataIndex: 'id',
            key: 'id',

        },
        {
            title: '用户名',
            dataIndex: 'username',
            key: 'username'
        },
        {
            title: '手机号',
            dataIndex: 'phone',
            key: 'phone'
        },
        {
            title: '姓名',
            dataIndex: 'name',
            key: 'name'
        },
    ];

    return (
        <div className={userList}>
            <Table
                rowKey='id'
                columns={columns}
                dataSource={dataSource}
                loading={loading}
                pagination= {false}
            />
            <Pagination
                className="ant-table-pagination" 
                total={total} 
            />
        </div>
    );
};

UserList.protoTypes = {
    dataSource: PropTypes.array,
    loading: PropTypes.any
};

export default UserList;