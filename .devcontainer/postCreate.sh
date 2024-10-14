# install the code generators for vscode and theia extensions
npm install -g yo generator-code generator-theia-extension @theia/generator-plugin @vscode/vsce

# trigger an initial build to have the dependencies installed in advance
yarn

# install the ECF Jakarta-RS libraries to the local Maven cache
cd /workspaces/monolith-single_deployment/service-application
chmod 777 ./install_ecf_jakartars.sh
./install_ecf_jakartars.sh

# trigger an initial build of the service application to have the Maven dependencies in place for further builds
mvn clean verify
