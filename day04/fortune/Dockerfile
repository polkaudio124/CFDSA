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

HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 \
	CMD curl localhost:${APP_PORT}/healthz || exit 1

EXPOSE ${APP_PORT}

ENTRYPOINT node main


# docker build -t ngch/cfdsa-fortune:1.0.0-alpha .
# docker build -t ngch/cfdsa-fortune:1.1.0 . 
# docker run -d -p 8080:3000 ngch/cfdsa-fortune:1.1.0
# docker run -d -p 9090:3000 ngch/cfdsa-fortune:1.0.0-alpha