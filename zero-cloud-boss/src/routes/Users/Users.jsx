import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'dva';
import { userContainer } from './index.css';
import UserList from '../../components/UserList/UserList';
import User from '../../components/AddUser/AddUser';
import SearchBar from '../../components/SearchBar/SearchBar';
import SearchForm from '../../components/SearchForm/SearchForm';

function genUsers({ dispatch, users }) {
    const {
        loading,
        total,
        current,
        list,
        currentItem,
        editorVisible,
        editorType
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
        },
        onModify(user) {

            dispatch({
                type: 'users/showEditor',
                payload: {
                    currentItem: user,
                    editorType: 'modify'
                }
            })
        },
        onDetail(user) {
            dispatch({
                type: 'users/showEditor',
                payload: {
                    currentItem: user,
                    editorType: 'detail'
                }
            })
        }
    };

    const userEditor = {
        user: editorType === 'create' ? {} : currentItem,
        type: editorType,
        disabled: editorType === 'detail',
        visible: editorVisible,
        onConfirm(data) {
            console.log(users);
            dispatch({
                type: `users/${editorType}`,
                payload: data
            });
        },
        onCancel() {
            dispatch({
                type: 'users/resetUser'
            });
        }
    }


    const userSearchProps = {
        fieldName: 'userName',
        labelName: '用户名称',
        onSearch(fieldValues) {

        }
    }

    return (
        <div>
            {
                editorVisible ?
                    (
                        (
                            <div>
                                <User {...userEditor} />
                            </div>
                        )
                    )
                    :
                    (
                        <div>
                            <SearchBar>
                                <SearchForm {...userSearchProps} />
                            </SearchBar>
                            <UserList {...userListProps} />
                        </div>
                    )
            }
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