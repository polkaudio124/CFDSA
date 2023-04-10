FROM node:19

WORKDIR /app

COPY main.js .
COPY package.json .
COPY package-lock.json .

COPY public public
COPY views views

RUN npm config set strict-ssl false
RUN npm ci

ENV APP_PORT=3000

EXPOSE ${APP_PORT}

ENTRYPOINT node main


# docker build -t ngch/cfdsa-fortune:1.0.0-alpha .
