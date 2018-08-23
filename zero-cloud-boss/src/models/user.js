import { query } from '../services/users';
import { parse } from 'qs';

export default {
    namespace: 'users',
    state: {
        loading: false,
        total: 50,
        list: [],
    },
    subscriptions: {
        setup({ dispatch, history }) {
            history.listen((location) => {
                if (location.pathname === '/users') {
                    dispatch({ type: 'query', payload: {} });
                }
            })
        }
    },
    effects: {
        *query({ payload }, { call, put }) {
            yield put({ type: 'showLoading' });
            const { data } = yield call(query, parse(payload));
            if (data) {
                yield put({
                    type: 'querySuccess',
                    payload: {list: data},
                });
            }
        }
    },
    reducers: {
        query(state, action) {
            return {...state, ...action.payload};
        },
        showLoading(state, action){
            return {...state, loading: true};
        },
        querySuccess(state, action){
            return {...state, ...action.payload, loading: false};
        },
    },
}