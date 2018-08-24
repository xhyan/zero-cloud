import React from 'react';
import PropTypes from 'prop-types';
import { Table, Pagination } from 'antd';
import { userList } from './index.css';
import { PAGE_SIZE } from '../../constants/constants';

const UserList = ({
    loading,
    total,
    current,
    dataSource,
    onPageChange
}) => {
    const columns = [
        {
            title: '序号',
            dataIndex: 'id',
            key: 'id',

        },
        {
            title: '用户名',
            dataIndex: 'loginName',
            key: 'loginName'
        },
        {
            title: '凭证号',
            dataIndex: 'identityCard',
            key: 'identityCard'
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
                current={parseInt(current, 10)}
                pageSize={PAGE_SIZE}
                onChange={onPageChange}
            />
        </div>
    );
};

UserList.protoTypes = {
    loading: PropTypes.any,
    total: PropTypes.number,
    current: PropTypes.number,
    dataSource: PropTypes.array,
    onPageChange: PropTypes.func,
};

export default UserList;