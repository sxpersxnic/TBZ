// use band_mgmt;

const bandId1 = new ObjectId();
const bandId2 = new ObjectId();
const bandId3 = new ObjectId();
db.bands.insertMany([
	{ _id: bandId1, name: "Nirvana", formed: new Date("1988-03-19"), genre: "Grunge" },
	{ _id: bandId2, name: "The Beatles", formed: new Date("1960-08-01"), genre: "Rock" },
	{ _id: bandId3, name: "The Rolling Stones", formed: new Date("1962-07-12"), genre: "Rock" }
]);

const musicianId1 = new ObjectId();
const musicianId2 = new ObjectId();
const musicianId3 = new ObjectId();
db.musicians.insertMany([
	{ _id: musicianId1, name: "Kurt Cobain", birth_date: new Date("1967-02-20"), instrument: "Vocals/Guitar", Band: bandId1 },
	{ _id: musicianId2, name: "John Lennon", birth_date: new Date("1940-10-09"), instrument: "Vocals/Guitar", Band: bandId2 },
	{ _id: musicianId3, name: "Mick Jagger", birth_date: new Date("1943-07-26"), instrument: "Vocals", Band: bandId3 }
]);

const albumId1 = new ObjectId();
const albumId2 = new ObjectId();
const albumId3 = new ObjectId();
const albumId4 = new ObjectId();

db.albums.insertMany([
	{ 
		_id: albumId1,
		title: "Nevermind",
		release_date: new Date("1991-09-24"),
		Band: bandId1,
		songs: [
			{
				title: "Smells Like Teen Spirit",
				duration: 301
			},
			{
				title: "Come As You Are",
				duration: 218
			}
		]
	},
	{
		_id: albumId2,
		title: "Abbey Road",
		release_date: new Date("1969-09-26"),
		Band: bandId2,
		songs: [
			{
				title: "Come Together",
				duration: 259
			},
			{
				title: "Something",
				duration: 182
			}
		]
	},
	{
		_id: albumId3,
		title: "Sticky Fingers",
		release_date: new Date("1971-04-23"),
		Band: bandId3,
		songs: [
			{
				title: "Brown Sugar",
				duration: 212
			},
			{
				title: "Wild Horses",
				duration: 320
			}
		]
	}
]);

db.albums.insertOne({
	_id: albumId4,
	title: "The Dark Side of the Moon",
	release_date: new Date("1973-03-01"),
	Band: bandId2,
	songs: [
		{
			title: "Speak to Me",
			duration: 90
		},
		{
			title: "Time",
			duration: 258
		}
	]
});
