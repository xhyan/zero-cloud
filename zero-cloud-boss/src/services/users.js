import request from '../utils/request';
import {ACCOUNT_API_URL, PAGE_SIZE} from '../constants/constants';

export async function query(payLoad) {
    const req = {
        pageSize:PAGE_SIZE,
        pageNo: 1,
        loginName: '',
        mobile: ''
    }

    return request(`http://${ACCOUNT_API_URL}/accounts/page`, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({...req, ...payLoad}),
    });
}

export async function create (data){
    return request(`http://${ACCOUNT_API_URL}/accounts`, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data),
    });
}

export async function modify (data){
    return request(`http://${ACCOUNT_API_URL}/accounts`, {
        method: "put",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data),
    });
}