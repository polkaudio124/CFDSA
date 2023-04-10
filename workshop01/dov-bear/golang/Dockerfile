# build main
FROM golang:1.20-alpine3.17 AS builder

ADD ./proxy.golang.org.crt /usr/local/share/ca-certificates/
RUN update-ca-certificates

WORKDIR /app

COPY main.go .
COPY go.mod .
COPY go.sum .

# runtime
RUN go build -o main .

FROM alpine:3.17

WORKDIR /app

COPY public public
COPY templates templates
COPY --from=builder /app/main .

ENV PORT=8000
ENV INSTANCE_NAME="NG CHIN HUI"
ENV INSTANCE_HASH="golang_default"

EXPOSE ${PORT}

HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 \
	CMD curl localhost:${PORT}/healthz || exit 1
	
ENTRYPOINT /app/main


# docker build -t ngch/cfdsa-dovbear:1.0.0-golang .
# docker run -d -p 8080:8000 ngch/cfdsa-dovbear:1.0.0-golang
# docker run -d -p 8080:8000 -e INSTANCE_HASH="da2459e9c362" ngch/cfdsa-dovbear:1.0.0-golang
# openssl s_client -showcerts -verify 5 -connect proxy.golang.org:443 < /dev/null