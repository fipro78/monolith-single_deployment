call mvn install:install-file ^
  -Dfile=org.eclipse.ecf.provider.jaxrs_1.7.1.202202112253.jar ^
  -DgroupId=org.eclipse.ecf ^
  -DartifactId=org.eclipse.ecf.provider.jaxrs ^
  -Dversion=1.7.1 ^
  -Dpackaging=jar

call mvn install:install-file ^
  -Dfile=org.eclipse.ecf.provider.jaxrs.server_1.11.1.202202112253.jar ^
  -DgroupId=org.eclipse.ecf ^
  -DartifactId=org.eclipse.ecf.provider.jaxrs.server ^
  -Dversion=1.11.1 ^
  -Dpackaging=jar

call mvn install:install-file ^
  -Dfile=org.eclipse.ecf.provider.jersey.server_1.11.1.202202112253.jar ^
  -DgroupId=org.eclipse.ecf ^
  -DartifactId=org.eclipse.ecf.provider.jersey.server ^
  -Dversion=1.11.1 ^
  -Dpackaging=jar

call mvn install:install-file ^
  -Dfile=org.eclipse.ecf.provider.jaxrs.client_1.8.1.202202112253.jar ^
  -DgroupId=org.eclipse.ecf ^
  -DartifactId=org.eclipse.ecf.provider.jaxrs.client ^
  -Dversion=1.8.1 ^
  -Dpackaging=jar

call mvn install:install-file ^
  -Dfile=org.eclipse.ecf.provider.jersey.client_1.8.2.202202112253.jar ^
  -DgroupId=org.eclipse.ecf ^
  -DartifactId=org.eclipse.ecf.provider.jersey.client ^
  -Dversion=1.8.2 ^
  -Dpackaging=jar