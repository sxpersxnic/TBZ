# MongoDB 06

## A. JSON Schemas erstellen

### Bands

#### Schema

```json
{
  "$jsonSchema": {
    "bsonType": "object",
    "required": [
      "_id",
      "formed",
      "genre",
      "name"
    ],
    "properties": {
      "_id": {
        "bsonType": "objectId"
      },
      "formed": {
        "bsonType": "date"
      },
      "genre": {
        "bsonType": "string"
      },
      "name": {
        "bsonType": "string"
      },
      "origin": {
        "bsonType": "string"
      }
    }
  }
}
```

#### Example

```json
{
  "_id": {
    "$oid": "6842f195d26a14425e5cd8a4"
  },
  "name": "Nirvana",
  "formed": {
    "$date": "1988-03-19T00:00:00.000Z"
  },
  "genre": "Grunge"
}
```

### Albums

### Musicians

#### Schema

```json
{
  "$jsonSchema": {
    "bsonType": "object",
    "required": [
      "_id",
      "Band",
      "birth_date",
      "instrument",
      "name"
    ],
    "properties": {
      "_id": {
        "bsonType": "objectId"
      },
      "Band": {
        "bsonType": "objectId"
      },
      "birth_date": {
        "bsonType": "date"
      },
      "instrument": {
        "bsonType": "string"
      },
      "name": {
        "bsonType": "string"
      }
    }
  }
}
```

#### Example

```json
{
  "_id": {
    "$oid": "6842f195d26a14425e5cd8aa"
  },
  "title": "Nevermind",
  "release_date": {
    "$date": "1991-09-24T00:00:00.000Z"
  },
  "Band": {
    "$oid": "6842f195d26a14425e5cd8a4"
  },
  "songs": [
    {
      "title": "Smells Like Teen Spirit",
      "duration": 301
    },
    {
      "title": "Come As You Are",
      "duration": 218
    }
  ]
}
```

#### Schema

```json
{
  "$jsonSchema": {
    "bsonType": "object",
    "required": [
      "_id",
      "Band",
      "release_date",
      "songs",
      "title"
    ],
    "properties": {
      "_id": {
        "bsonType": "objectId"
      },
      "Band": {
        "bsonType": "objectId"
      },
      "release_date": {
        "bsonType": "date"
      },
      "songs": {
        "bsonType": "array",
        "items": {
          "bsonType": "object",
          "properties": {
            "duration": {
              "bsonType": "int"
            },
            "title": {
              "bsonType": "string"
            }
          },
          "required": [
            "duration",
            "title"
          ]
        }
      },
      "title": {
        "bsonType": "string"
      }
    }
  }
}
```

#### Example

```json
{
  "_id": {
    "$oid": "6842f195d26a14425e5cd8a7"
  },
  "name": "Kurt Cobain",
  "birth_date": {
    "$date": {
      "$numberLong": "-90374400000"
    }
  },
  "instrument": "Vocals/Guitar",
  "Band": {
    "$oid": "6842f195d26a14425e5cd8a4"
  }
}
```

## B. Validierung hinterlegen und testen

- **Screenshot of valid Validation:**
	![Valid Validation](/m165-NoSQL/x-resources/m/06/valid-validation.png)

- **Commands:**
	- [Create Validation](./collection-validation.mongosh.txt)
	- [Create Validation Role](./collection-validation.mongosh.txt)
	- Read Validation: `db.getCollectionInfos()`
- **Screenshot of invalid Validation:**
	![Invalid Validation](/m165-NoSQL/x-resources/m/06/invalid-insert.png)
- **Screenshot of valid Validation:**
	![Valid Validation](/m165-NoSQL/x-resources/m/06/valid-insert.png)
