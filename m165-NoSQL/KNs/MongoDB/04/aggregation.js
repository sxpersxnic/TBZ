// use band_mgmt;

// 1. aggregation with $match in several stages,
// corresponds to AND operation as with find({ $and: [...] })
// Explanation: Two separate filter phases to concatenate conditions.
db.bands.aggregate([
  { $match: { genre: "Rock" } },
  { $match: { origin: { $exists: true } } }
]);

/* -------------------------------------------------------------------- */

// 2. aggregation with $match, $project, $sort, return of several documents
// Explanation: Filters guitarists, shows only relevant fields without _id,
// and sorts in ascending order by date of birth.
db.musicians.aggregate([
  { $match: { instrument: { $regex: /Guitar/i } } },
  { $project: { _id: 0, name: 1, instrument: 1, birth_date: 1 } },
  { $sort: { birth_date: 1 } }
]);

/* -------------------------------------------------------------------- */

// 3. aggregation with $sum for counting (count)
// Explanation: Groups albums by band ID and counts albums.
db.albums.aggregate([
  {
    $group: {
      _id: "$Band",
      totalAlbums: { $sum: 1 }
    }
  }
]);

/* -------------------------------------------------------------------- */

// 4. aggregation with $group for sum of song duration per album
// Explanation: Calculates total duration of songs per album and sorts in descending order.
db.albums.aggregate([
  {
    $unwind: "$songs"
  },
  {
    $group: {
      _id: "$_id",
      albumTitle: { $first: "$title" },
      totalDuration: { $sum: "$songs.duration" }
    }
  },
  {
    $sort: { totalDuration: -1 }
  }
]);
