import React from 'react';
import { Router, Route } from 'dva/router';
// import Products from './routes/Products';
import Users from './routes/Users/Users';


export default function ({ history }) {
  return (
    <Router history={history}>
      <div>
        <Route path="/users" component={Users} />>
    </div>
    </Router>
  );
};
