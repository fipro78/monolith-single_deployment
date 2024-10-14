call mvn install --file jakartars-provider-bundles/parent-pom.xml

call mvn install:install-file ^
  -Dfile=jakartars-provider-bundles/org.eclipse.ecf.provider.jakartars-1.0.0-SNAPSHOT.jar ^
  -DgroupId=org.eclipse.ecf.jakartars ^
  -DartifactId=org.eclipse.ecf.provider.jakartars ^
  -Dversion=1.0.0-SNAPSHOT ^
  -Dpackaging=jar

call mvn install:install-file ^
  -Dfile=jakartars-provider-bundles/org.eclipse.ecf.provider.jakartars.server-1.0.0-SNAPSHOT.jar ^
  -DgroupId=org.eclipse.ecf.jakartars ^
  -DartifactId=org.eclipse.ecf.provider.jakartars.server ^
  -Dversion=1.0.0-SNAPSHOT ^
  -Dpackaging=jar

call mvn install:install-file ^
  -Dfile=jakartars-provider-bundles/org.eclipse.ecf.provider.jersey.server-1.0.0-SNAPSHOT.jar ^
  -DgroupId=org.eclipse.ecf.jakartars ^
  -DartifactId=org.eclipse.ecf.provider.jersey.server ^
  -Dversion=1.0.0-SNAPSHOT ^
  -Dpackaging=jar

call mvn install:install-file ^
  -Dfile=jakartars-provider-bundles/org.eclipse.ecf.provider.jakartars.client-1.0.0-SNAPSHOT.jar ^
  -DgroupId=org.eclipse.ecf.jakartars ^
  -DartifactId=org.eclipse.ecf.provider.jakartars.client ^
  -Dversion=1.0.0-SNAPSHOT ^
  -Dpackaging=jar

call mvn install:install-file ^
  -Dfile=jakartars-provider-bundles/org.eclipse.ecf.provider.jersey.client-1.0.0-SNAPSHOT.jar ^
  -DgroupId=org.eclipse.ecf.jakartars ^
  -DartifactId=org.eclipse.ecf.provider.jersey.client ^
  -Dversion=1.0.0-SNAPSHOT ^
  -Dpackaging=jar