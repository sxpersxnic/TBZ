import PropTypes from 'prop-types';
import { Button, Col, Form, Row } from 'react-bootstrap';
import './AddIngredient.css';

const AddIngredient = ({ ingredient, updateIngredient, removeIngredient }) => {
	const handleChange = (e) => {
		const { name, value } = e.target;
		updateIngredient({ ...ingredient, [name]: value });
	};

	return (
		<Row>
			<Col>
				<Form.Group
					className='mb-1'
					controlId={`ingredientName-${ingredient.listId}`}>
					<Form.Control
						placeholder='Ingredient Name'
						name='name'
						value={ingredient.name || ''}
						onChange={handleChange}
						required
					/>
				</Form.Group>
			</Col>
			<Col>
				<Form.Group
					className='mb-1'
					controlId={`ingredientUnit-${ingredient.listId}`}>
					<Form.Select
						name='unit'
						value={ingredient.unit || 'PIECE'}
						onChange={handleChange}>
						<option value='PIECE'>PIECE</option>
						<option value='GRAMM'>GRAMM</option>
						<option value='KILOGRAMM'>KILOGRAMM</option>
						<option value='LITRE'>LITRE</option>
						<option value='DECILITRE'>DECILITRE</option>
					</Form.Select>
				</Form.Group>
			</Col>
			<Col>
				<Form.Group
					className='mb-1'
					controlId={`ingredientAmount-${ingredient.listId}`}>
					<Form.Control
						placeholder='Amount'
						name='amount'
						type='number'
						min='1'
						value={ingredient.amount || ''}
						onChange={handleChange}
						required
					/>
				</Form.Group>
			</Col>
			<Col xs={1}>
				<Button
					onClick={(e) => removeIngredient(ingredient)}
					variant='outline-dark'
					className='mb-1'
					type='button'>
					x
				</Button>
			</Col>
		</Row>
	);
};

AddIngredient.propTypes = {
	ingredient: PropTypes.shape({
		listId: PropTypes.number.isRequired,
		name: PropTypes.string,
		unit: PropTypes.string,
		amount: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
		comment: PropTypes.string,
	}).isRequired,
	updateIngredient: PropTypes.func.isRequired,
	removeIngredient: PropTypes.func.isRequired,
};

AddIngredient.defaultProps = {};

export default AddIngredient;

