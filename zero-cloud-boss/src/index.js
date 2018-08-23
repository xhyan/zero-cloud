import dva from 'dva';
import { browserHistory } from 'dva/router';
import router from './router';
import users from './models/user';
import products from './models/products';
import './index.css';


// 1. Initialize
const app = dva({
    history: browserHistory,
});

// 2. Plugins
// app.use(createLoading());

// 3. Model
app.model(users);
app.model(products);
// 4. Router
app.router(router);

// 5. Start
app.start('#root');