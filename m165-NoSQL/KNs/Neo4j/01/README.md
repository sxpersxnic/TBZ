# Neo4j 01

## A. Installation

![Evidence of connection](/m165-NoSQL/x-resources/n/01/connection.png)

## B. Logical Model for Neo4j

### Diagram

- [Original File](/m165-NoSQL/x-resources/n/01/logical-model.drawio)
- **Diagram:**

  ![Image of logical model](/m165-NoSQL/x-resources/n/01/logical-model.png)

### Explanation of Attributes

- **BAND**:
	- `id`: Unique identifier.
	- `name`: Name of the band.
	- `formed`: Date the band was formed.
	- `genre`: Genre of the band.
- **MUSICIAN**:
	- `id`: Unique identifier.
	- `name`: Name of the musician.
	- `birth_date`: Date of birth of the musician.
	- `instrument`: Instrument played by the musician.
- **ALBUM**:
	- `id`: Unique identifier.
	- `title`: Title of the album.
	- `release_date`: Release date of the album.
- **SONG**:
	- `id`: Unique identifier.
	- `title`: Title of the song.
	- `duration`: Duration of the song in seconds.

**Relationships**:
- **BAND** employs **MUSICIAN**: 1:N relationship.
- **BAND** produces **ALBUM**: 1:N relationship.
- **ALBUM** contains **SONG**: 1:N relationship.
- **MUSICIAN** performs **SONG**: N:M relationship.
