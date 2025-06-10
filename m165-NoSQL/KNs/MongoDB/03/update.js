// use band_mgmt;

db.bands.updateOne(
  { _id: ObjectId('683072dd4d0e44bee6cf466a') },
  { $set: { genre: "Alternative Rock" } }
);

/* -------------------------------------------------------------------- */

db.musicians.updateMany(
  {
    $or: [
      { instrument: "Vocals" },
      { instrument: "Vocals/Guitar" }
    ]
  },
  {
    $set: { instrument: "Vocals & Guitar" }
  }
);

/* -------------------------------------------------------------------- */

db.albums.replaceOne(
  { title: "Nevermind" },
  {
    title: "Nevermind (Remastered)",
    release_date: new Date("1993-01-01"),
    Band: ObjectId('6842e68e224bbc64d95da5c2'),
    songs: [
      { title: "Smells Like Teen Spirit", duration: 301 },
      { title: "Come As You Are", duration: 218 },
      { title: "Lithium", duration: 257 }
    ]
  }
);
