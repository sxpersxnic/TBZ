import { Button, Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import './Recipe.css';

function Recipe(props) {
	return (
		<Card style={{ width: '18rem' }}>
			<Card.Img
				variant='top'
				src={props.image}
			/>
			<Card.Body>
				<Card.Title>{props.title}</Card.Title>
				{props.description}
				<Link to={`/edit-recipe/${props.id}`}>
					<Button variant='primary'>Edit Details</Button>
				</Link>
			</Card.Body>
		</Card>
	);
}

export default Recipe;
