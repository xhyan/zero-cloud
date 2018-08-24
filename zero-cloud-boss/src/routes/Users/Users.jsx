import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'dva';
import { userContainer } from './index.css';
import UserList from '../../components/UserList/UserList';

function genUsers({ dispatch, users }) {
    const {
        loading,
        total,
        current,
        list,
    } = users;

    const userListProps = {
        loading,
        total,
        current,
        dataSource: list,
        onPageChange(page) {
            dispatch({
                type: 'users/query',
                payload: { pageNumber: page }
            })
        }
    };

    return (
        <div className={userContainer}>
            <UserList {...userListProps} />
        </div>
    );
}

class Users extends Component {
    render() {
        return genUsers(this.props);
    }
}

Users.propTypes = {
    users: PropTypes.object
};

function mapStateToProps({ users }) {
    return { users };
}

export default connect(mapStateToProps)(Users);