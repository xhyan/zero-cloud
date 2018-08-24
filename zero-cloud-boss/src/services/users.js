import request from '../utils/request';
import {ACCOUNT_API_URL, PAGE_SIZE} from '../constants/constants';

export async function query(req) {
    req.pageSize = PAGE_SIZE;

    return request(`http://${ACCOUNT_API_URL}/accounts/page`, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(req),
    });
}