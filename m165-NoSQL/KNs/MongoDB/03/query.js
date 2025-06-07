// use band_mgmt;

db.bands.find(
  { name: { $regex: /The/i } },
  { _id: 0, name: 1, genre: 1 }
);

/* -------------------------------------------------------------------- */

db.musicians.find({
  $and: [
    { instrument: { $regex: /Guitar/i } },
    { name: { $regex: /John/i } }
  ]
});

/* -------------------------------------------------------------------- */

db.albums.find({
  $or: [
    { title: "Abbey Road" },
    { title: "Sticky Fingers" }
  ]
});

/* -------------------------------------------------------------------- */

db.bands.find({
  formed: { $gt: new Date("1970-01-01") }
});

/* -------------------------------------------------------------------- */

db.musicians.find(
  { instrument: { $regex: /Vocals/i } },
  { _id: 1, name: 1, instrument: 1 }
);
