# Build runtime image
FROM mcr.microsoft.com/dotnet/aspnet:8.0-alpine
WORKDIR /App
COPY bin .
EXPOSE 8080
ENTRYPOINT ["dotnet", "/App/account.dll"]