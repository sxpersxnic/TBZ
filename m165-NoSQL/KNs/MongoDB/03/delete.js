// use band_mgmt;

db.musicians.deleteOne({
  _id: ObjectId('683072eb4d0e44bee6cf466b')
});

/* -------------------------------------------------------------------- */

db.albums.deleteMany({
  $or: [
    { title: "Sticky Fingers" },
    { title: "Abbey Road" }
  ]
});
