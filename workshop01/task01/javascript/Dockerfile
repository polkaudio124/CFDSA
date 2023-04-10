FROM node:19

WORKDIR /app

COPY main.js .
COPY package.json .
COPY package-lock.json .

COPY public public
COPY views views

RUN npm config set strict-ssl false
RUN npm ci

ENV PORT=8000
ENV INSTANCE_NAME="NG CHIN HUI"
ENV INSTANCE_HASH="Hello World! Welcome!"

HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 \
	CMD curl localhost:${PORT}/healthz || exit 1

EXPOSE ${PORT}

ENTRYPOINT node main


# docker build -t ngch/cfdsa-dovbear:1.0.0 .
# docker run -d -p 8080:8000 ngch/cfdsa-dovbear:1.0.0
# docker run -d -p 8080:8000 -e INSTANCE_HASH="da2459e9c362" ngch/cfdsa-dovbear:1.0.0
