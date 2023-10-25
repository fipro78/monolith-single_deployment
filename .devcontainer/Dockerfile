ARG VARIANT="17"

FROM mcr.microsoft.com/vscode/devcontainers/java:${VARIANT}

# Enable the following section if you are behind a corporate proxy

# ARG PROXY_HOST=host.docker.internal
# ARG PROXY_PORT=3128

# ENV http_proxy="http://${PROXY_HOST}:${PROXY_PORT}"
# ENV https_proxy=${http_proxy}
# ENV GLOBAL_AGENT_HTTP_PROXY=${http_proxy}
# ENV GLOBAL_AGENT_HTTPS_PROXY=${http_proxy}
# ENV ELECTRON_GET_USE_PROXY true
# ENV PUPPETEER_SKIP_CHROMIUM_DOWNLOAD true

# ENV PROXY_HOST=${PROXY_HOST}
# ENV PROXY_PORT=${PROXY_PORT}

# COPY ./settings.xml /root/.m2/settings.xml

RUN echo 'alias ll="ls -l"' >> ~/.bashrc

# Theia dependencies
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
     && apt-get -y install --no-install-recommends \
     make \
     gcc \
	 dos2unix \
     pkg-config \
     build-essential \
     python3.10 \
     software-properties-common \
     libx11-dev \
     libxkbfile-dev \
     libsecret-1-dev \
     libssl-dev

USER root

EXPOSE 3000