// use band_mgmt;

db.createCollection("bands", {
  validator: {
    $jsonSchema: {
      "bsonType": "object",
      "title": "Band",
      "required": ["name", "formed"],
      "properties": {
        "name": {
          "bsonType": "string"
        },
        "formed": {
          "bsonType": "date"
        },
        "origin": {
          "bsonType": "string"
        },
        "genre": {
          "bsonType": "string"
        }
      }
    }
  }
});

db.createCollection("musicians", {
  validator: {
    $jsonSchema: {
      "bsonType": "object",
      "title": "Musician",
      "required": ["name", "birth_date", "instrument", "Band"],
      "properties": {
        "name": {
          "bsonType": "string"
        },
        "birth_date": {
          "bsonType": "date"
        },
        "instrument": {
          "bsonType": "string"
        },
        "Band": {
          "bsonType": "objectId"
        }
      }
    }
  }
});

db.createCollection("albums", {
  validator: {
    $jsonSchema: {
      "bsonType": "object",
      "title": "Album",
      "required": ["title", "release_date", "Band", "songs"],
      "properties": {
        "title": {
          "bsonType": "string"
        },
        "release_date": {
          "bsonType": "date"
        },
        "Band": {
          "bsonType": "objectId"
        },
        "songs": {
          "bsonType": "array",
          "items": {
            "title": "object",
            "required": ["title", "duration"],
            "properties": {
              "title": {
                "bsonType": "string"
              },
              "duration": {
                "bsonType": "int"
              }
            }
          }
        }
      }
    }
  }
});

/* -------------------------------------------------------------------- */

// Sample Data
const bandId = ObjectId();
db.bands.insertOne({
  _id: bandId,
  name: "The Sonic Pulse",
  formed: new Date("2010-05-15"),
  genre: "Rock"
});

const musician1Id = ObjectId();
const musician2Id = ObjectId();
db.musicians.insertMany([
  {
    _id: musician1Id,
    name: "Alice Reed",
    birth_date: new Date("1985-07-20"),
    instrument: "Guitar",
    Band: bandId
  },
  {
    _id: musician2Id,
    name: "Tom Vega",
    birth_date: new Date("1987-11-03"),
    instrument: "Drums",
    Band: bandId
  }
]);

const albumId = ObjectId();
db.albums.insertOne({
  _id: albumId,
  title: "Echoes of Tomorrow",
  release_date: new Date("2022-08-15"),
  Band: bandId,
  songs: [
    {
      title: "Hit the Road",
      duration: 210
    },
    {
      title: "Nightfall",
      duration: 185
    }
  ]
});
