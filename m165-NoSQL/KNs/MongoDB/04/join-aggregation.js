// use band_mgmt;

// 1. $lookup to join musicians with associated band info
// Explanation: Each musician gets their band object added as a field.
db.musicians.aggregate([
  {
    $lookup: {
      from: "bands",
      localField: "Band",
      foreignField: "_id",
      as: "bandInfo"
    }
  },
  { $unwind: "$bandInfo" },
  {
    $project: {
      name: 1,
      instrument: 1,
      bandName: "$bandInfo.name",
      bandGenre: "$bandInfo.genre"
    }
  }
]);

/* -------------------------------------------------------------------- */

// 2. advanced join with filter: find musicians in rock bands, play guitar
db.musicians.aggregate([
  {
    $lookup: {
      from: "bands",
      localField: "Band",
      foreignField: "_id",
      as: "bandInfo"
    }
  },
  { $unwind: "$bandInfo" },
  {
    $match: {
      "bandInfo.genre": "Rock",
      instrument: { $regex: /Guitar/i }
    }
  },
  {
    $project: {
      name: 1,
      instrument: 1,
      bandName: "$bandInfo.name"
    }
  }
]);
