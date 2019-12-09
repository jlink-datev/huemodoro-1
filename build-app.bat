cd frontend
call npm run-script lint
call npm run-script test
call npm run-script build
cd ..
call mvn clean package
