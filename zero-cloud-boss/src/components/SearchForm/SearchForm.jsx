
import { Button, Form, Input } from 'antd';
import { searchForm } from './index.css';
import PropTypes from 'prop-types';
import 'antd/dist/antd.css';


const FormItem = Form.Item;

const SearchForm = ({
    onSearch,
    fieldName,
    labelName,
    form: {
        getFieldDecorator,
        getFieldsValue
    }
}) => {
    const onSubmit = (e) => {
        e.preventDefault();
        onSearch(getFieldsValue());
    };

    return (
        <div className={searchForm}>
            <Form layout='inline' onSubmit={onSubmit}>
                <FormItem label={labelName}>
                    {
                        getFieldDecorator(fieldName)(
                            <Input type="text" />
                        )
                    }
                </FormItem>
                <Button type='primary' htmlType='submit'>查询</Button>
            </Form>
        </div>
    );
}

SearchForm.propTypes = {
    form: PropTypes.object,
    onSearch: PropTypes.func, 
    customers: PropTypes.array
};

export default Form.create()(SearchForm);
