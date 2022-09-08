# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.231.5/containers/typescript-node/.devcontainer/base.Dockerfile

# Node.js version (use -bullseye variants on local arm64/Apple Silicon): 16, 14, 12, 16-bullseye, 14-bullseye, 12-bullseye, 16-buster, 14-buster, 12-buster
ARG VARIANT="14"
FROM mcr.microsoft.com/vscode/devcontainers/typescript-node:0-${VARIANT}

ENV http_proxy http://host.docker.internal:3128
ENV https_proxy http://host.docker.internal:3128
ENV GLOBAL_AGENT_HTTP_PROXY http://host.docker.internal:3128
ENV GLOBAL_AGENT_HTTPS_PROXY http://host.docker.internal:3128
ENV ELECTRON_GET_USE_PROXY true

# install code generator for vs code
RUN npm install -g yo code generator-code

USER node

EXPOSE 3000