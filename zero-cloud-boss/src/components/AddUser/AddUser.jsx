import { addUser, userWrapper, buttonGroup, confirmButton, cancelButton } from "./index.css";
import AddUserTitle from "../UserTitle/UserTitle";
import AddUserForm from "../UserForm/UserForm";
import { Button } from "antd";


const AddUser = ({ user, disabled, onConfirm, onCancel }) => {


    return (
        <div className={addUser}>
            <div className={userWrapper}>
                <AddUserTitle titleText='用户信息' />
                <AddUserForm user={user} disable={disabled} />
            </div>
            <div className={buttonGroup}>
                {
                    disabled ?
                        null :
                        <Button type="primary" className={confirmButton} onClick={onConfirm}>确定</Button>
                }
                <Button type="ghost" className={cancelButton} onClick={onCancel}>取消</Button>
            </div>
        </div>
    );

}


export default AddUser;


