# 用户 User

| **属性名称**                           | **属性类型** | **is null** | **primary key** |
| -------------------------------------- | ------------ | ----------- | --------------- |
| user_account                           | varchar(30)  | no          | yes             |
| user_password                          | varchar(30)  | no          | no              |
| user_email                             | varchar(30)  | no          | no              |
| user_role(用户角色-customer0，admin-1) | int          | no          | no              |

# 商品 Good

| **属性名称** | **属性类型** | **is null** | **primary key** |
| ------------ | ------------ | ----------- | --------------- |
| good_name    | varchar(30)  | no          | yes             |
| good_price   | double       | no          | no              |
| good_img     | varchar(100) | no          | no              |
| good_sale    | int          | no          | no              |
| good_info    | varchar(100) | no          | no              |
| good_type    | varchar(30)  | no          | no              |

# 购物车Cart

| **属性名称**           | **属性类型** | **is null** | **primary key** |
| ---------------------- | ------------ | ----------- | --------------- |
| user_account(关联外键) | varchar(30)  | no          | no              |
| good_name(关联外键)    | varchar(30)  | no          | no              |
| good_price             | double       | no          | no              |
| good_number            | int          | no          | no              |

# 订单 Bill

| **属性名称**           | **属性类型** | **is null** | **primary key** |
| ---------------------- | ------------ | ----------- | --------------- |
| user_account(关联外键) | varchar(30)  | no          | no              |
| good_name(关联外键)    | varchar(30)  | no          | no              |
| good_price             | double       | no          | no              |
| order_time             | varchar(30)  | no          | no              |

# 顾客购买日志 BuyLog

| **属性名称**           | **属性类型** | **is null** | **primary key** |
| ---------------------- | ------------ | ----------- | --------------- |
| user_account(关联外键) | varchar(30)  | no          | no              |
| good_name(关联外键)    | varchar(30)  | no          | no              |
| good_number            | int          | no          | no              |
| buy_time               | varchar(30)  | no          | no              |

# 顾客浏览日志 BrowseLog

| **属性名称**           | **属性类型** | **is null** | **prmairy key** |
| ---------------------- | ------------ | ----------- | --------------- |
| user_account(关联外键) | varchar(30)  | no          | no              |
| good_name(关联外键)    | varchar(30)  | no          | no              |
| browse_time            | varchar(30)  | no          | no              |
