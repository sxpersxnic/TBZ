// use band_mgmt;

// 1. simple configuration of individual fields of sub-documents (e.g. song title)
db.albums.aggregate([
  {
    $project: {
      title: 1,
      songTitles: "$songs.title"
    }
  }
]);

/* -------------------------------------------------------------------- */

// 2. filtering by sub-document field, e.g. songs with duration > 200 seconds
db.albums.aggregate([
  {
    $match: {
      "songs.duration": { $gt: 200 }
    }
  }
]);

/* -------------------------------------------------------------------- */

// 3. $unwind to “flatten” array songs (one document per song)
db.albums.aggregate([
  {
    $unwind: "$songs"
  },
  {
    $project: {
      albumTitle: "$title",
      songTitle: "$songs.title",
      duration: "$songs.duration"
    }
  },
  {
    $sort: { duration: -1 }
  }
]);
