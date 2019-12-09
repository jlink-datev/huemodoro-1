#!/usr/bin/env bash
cd frontend
npm run-script lint
npm run-script test
npm run-script build
cd ..
mvn clean package
# "Deploy" fuer Arme
cp ./web/target *.jar ./docs/releases
cp run-app.bat ./docs/releases
cp run-app.sh ./docs/releases