import { query, create, modify } from '../services/users';
import { parse } from 'qs';

export default {
    namespace: 'users',
    state: {
        loading: false,
        total: 0,
        current: 0,
        list: [],
        onPageChange: {},
        editorVisible: false,
        editorType: 'create'
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
                    payload: {
                        total: data.total,
                        current: data.page,
                        list: data.data
                    },
                });
            }
        },
        *create({ payload }, { call, put }) {
            yield put({ type: 'hideEditor' });
            yield call(create, parse(payload));
            yield put({type: 'query'});

        },
        *modify({ payload }, { call, put }) {
            console.log(payload);
            yield put({ type: 'hideEditor' });
            yield call(modify, parse(payload));
            yield put({type: 'query'});
        }
    },
    reducers: {
        query(state, action) {
            return { ...state, ...action.payload };
        },
        showEditor(state, action) {
            return { ...state, ...action.payload, editorVisible: true };
        },
        hideEditor(state, action) {
            return { ...state, editorVisible: false };
        },
        showLoading(state, action) {
            return { ...state, loading: true };
        },
        querySuccess(state, action) {
            return { ...state, ...action.payload, loading: false };
        },
        resetUser(state, action) {
            return { ...state, currentItem: {}, editorVisible: false, editorType: 'create' }
        }
    },
}