# This is the web application development course design of SCUT. For the mission requirement, all the code and preparation resources are now open source on github.

------

# PRIMARY INFORMATION

- ## CHINESE NAME：Chen Yingsong    

- ## ENGLISH NAME: CHaron

- ## STUDENT NUMBER：202130442563

- ## CLASS: School of Computer Science and Engineering, Class 2 

- ##  URL OF WEBSITE：http://114.132.219.217/  (BTW, you can go to the website directly by clicking on this link)

------
# Description of files or folders in the main branch

- .idea: IDEA configuration files

- friend: vue project directory

- perk: springboot project directory

- previous sources & edition_2: project preparation data and the second version modified from the final version (unreleased),including teacher's handout

- screenshots: screenshot of the picture in the report

------

## The following accounts are available for testing（all the accounts' password is 123 !）:

- Chandler

- CHaron

- cys

- Gunther

- Joey

- Monica

- Phoebe

- Rachael

- Ross

- test

    ------

    # MATTERS THAT MUST NEED BEWARE !!  

    ​       **Please make sure you read the following content before browsing the web !**
    
    1. In the login interface, sometimes when you enter the correct account and password and click the login button, the system will display a pop-up window of successful login but does not automatically jump to the page, don't worry, this may be the system network is stuck, at this time click once or several times the login button can be.
    1. Due to restrictions, there are only 10 fixed items to choose from. If you want to add an item, you can only add one of these 10 items(So if you already have 10 items and you want to test the delete add function, you can delete one item before adding it!), and the picture name must match the item name, plus the.png suffix
    1. The email address must be filled in correctly (if it is correct, the message can be successfully sent), otherwise bugs will appear！
    1. Don't worry if you don't get no reaction when you try to modify or delete an item, it's probably due to a foreign key constraint that the item is sitting in your shopping cart or log.
    1. The Alipay account is rjytfm7636@sandbox.com , password is 111111(both interfaces require a password, and the password is 11111)
    1. When using Alipay to pay, do not click anywhere or refresh the screen except for entering the necessary account number and password, otherwise an error will occur!

------

# 2026 Local Development Supplement

This section only documents how to run the old course project on a modern local macOS environment.

## Runtime

- Frontend: Vue CLI project in `friend`
- Backend: Spring Boot project in `perk`
- Frontend API base path: `/api`
- Vue dev server proxy target: `http://127.0.0.1:9090`
- Backend default port: `9090`

## Backend

```bash
cd /Users/bytedance/Desktop/web_application_development/perk

export JAVA_HOME=/opt/homebrew/opt/openjdk
export PATH="$JAVA_HOME/bin:$PATH"
export SERVER_PORT=9090
export SHOP_DB_URL='jdbc:mysql://127.0.0.1:3307/shop?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true'
export SHOP_DB_USERNAME=root
export SHOP_DB_PASSWORD=
export APP_CORS_ALLOWED_ORIGIN_PATTERNS='http://localhost:8080,http://127.0.0.1:8080,http://localhost:8081,http://127.0.0.1:8081'

./mvnw spring-boot:run
```

Change `SHOP_DB_URL`, `SHOP_DB_USERNAME`, and `SHOP_DB_PASSWORD` if you use your own MySQL instance.

## Frontend

```bash
cd /Users/bytedance/Desktop/web_application_development/friend

export VUE_APP_API_BASE_URL=/api
export VUE_APP_API_PROXY_TARGET=http://127.0.0.1:9090

node node_modules/@vue/cli-service/bin/vue-cli-service.js serve --host 127.0.0.1 --port 8080
```

Open `http://127.0.0.1:8080/#/login`.

## Local Preview Accounts

If you use the temporary local seed data prepared during the 2026 cleanup, the available accounts are:

- `test / 123`
- `admin / 123`

The older account list above depends on importing the original full database data.

## Notes

- Do not stop the backend with `Ctrl+Z`; it pauses the Java process but keeps port `9090` occupied. Use `Ctrl+C`.
- If a port is occupied, clear local dev servers first:

```bash
for port in 8080 8081 9090; do
  pids=$(lsof -tiTCP:$port -sTCP:LISTEN || true)
  [ -n "$pids" ] && kill -CONT $pids 2>/dev/null || true
  [ -n "$pids" ] && kill $pids 2>/dev/null || true
done
```

- On newer JDKs, the backend explicitly configures Lombok annotation processing in `perk/pom.xml`.
- The frontend calls backend APIs through `/api/*`; `friend/vue.config.js` rewrites `/api` away before forwarding to the Spring Boot backend.
