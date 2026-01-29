# Code Reviews

- [App](../x-res/apps/recipe-planner-fronend-and-backend/recipe-planner-fronend)

## Run

**Frontend:**

```sh
cd ../x-res/apps/recipe-planner-fronend-and-backend/recipe-planner-frontend
npm install
npm start # starts the frontend at http://localhost:3000
```

**Backend:**

```sh
cd ../x-res/apps/recipe-planner-fronend-and-backend/recipe-planner-backend
mvn clean install
mvn spring-boot:run # starts the backend at http://localhost:8080
```

## Conclusion

- Code reviews require the reviewer to know the context of the application well.
- GitHub provides a feature to request reviews from specific copilot. This features speeds up the review process.
- The more code a pull request contains, the harder it is to review it properly.
- Code reviews can be done in pairs, where one developer writes the code and the other reviews it in real-time.
