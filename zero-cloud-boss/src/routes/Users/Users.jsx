import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'dva';
import UserList from '../../components/UserList/UserList';
import UserForm from '../../components/UserForm/UserForm';
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

    const userFormProps = {
        user: editorType === 'create' ? {} : currentItem,
        type: editorType,
        disabled: editorType === 'detail',
        visible: editorVisible,
        onConfirm(data) {
            console.log(data);
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

    const onAdd = () => {
        dispatch({
            type: 'users/showEditor',
            payload: {
                editorType: 'create'
            }
        });
    }

    const userSearchProps = {
        fieldName: 'mobile',
        labelName: '手机号',
        onSearch(fieldValues) {
            console.log(fieldValues)
            dispatch({
                type: 'users/query',
                payload: fieldValues
            })
        }
    }

    return (
        <div>
            {
                editorVisible ?
                    <UserForm {...userFormProps} />
                    : ""
            }
            <SearchBar onAdd={onAdd}>
                <SearchForm {...userSearchProps} />
            </SearchBar>
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