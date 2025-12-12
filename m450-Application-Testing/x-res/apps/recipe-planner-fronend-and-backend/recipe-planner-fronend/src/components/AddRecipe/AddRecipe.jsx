import { useState } from 'react';
import { Alert, Button, Col, Form, Row } from 'react-bootstrap';
import './AddRecipe.css';

import axios from 'axios';
import AddIngredient from '../AddIngredient/AddIngredient';

const baseURL = 'http://localhost:8080/api/recipes';

function AddRecipe() {
	const [formData, setFormData] = useState({
		name: '',
		description: '',
		imageUrl: '',
		ingredients: [],
		id: null,
	});
	const [submitStatus, setSubmitStatus] = useState({
		show: false,
		success: false,
		message: '',
	});

	const [listId, setListId] = useState(1);

	const handleInputChange = (e) => {
		const { name, value } = e.target;
		setFormData({ ...formData, [name]: value });
	};

	const addIngredient = () => {
		setFormData({
			...formData,
			ingredients: [
				...formData.ingredients,
				{
					listId: listId,
					name: '',
					unit: 'PIECE',
					amount: 1,
					comment: '',
				},
			],
		});
		setListId(listId + 1);
	};
	const updateIngredient = (ingredientObj) => {
		const updatedIngredients = formData.ingredients.map((ingredient) => {
			if (ingredient.listId === ingredientObj.listId) {
				return ingredientObj;
			}
			return ingredient;
		});
		setFormData({ ...formData, ingredients: updatedIngredients });
	};

	const removeIngredient = (ingredientObj) => {
		const updatedIngredients = formData.ingredients.filter(
			(ingredient) => ingredient.listId !== ingredientObj.listId
		);
		setFormData({ ...formData, ingredients: updatedIngredients });
	};

	const handleSubmit = async (e) => {
		e.preventDefault();
		setSubmitStatus({ show: false, success: false, message: '' });

		// Prepare the recipe data for the API
		const recipeData = {
			name: formData.name,
			description: formData.description,
			imageUrl: formData.imageUrl,
			ingredients: formData.ingredients.map((ing) => ({
				name: ing.name,
				unit: ing.unit,
				amount: parseInt(ing.amount) || 0,
				comment: ing.comment || '',
			})),
		};

		try {
			const response = await axios.post(baseURL, recipeData);
			setSubmitStatus({
				show: true,
				success: true,
				message: 'Recipe added successfully!',
			});
			// Reset form after successful submission
			setFormData({
				name: '',
				description: '',
				imageUrl: '',
				ingredients: [],
				id: null,
			});
			setListId(1);
		} catch (error) {
			setSubmitStatus({
				show: true,
				success: false,
				message: `Failed to add recipe: ${
					error.response?.data?.message || error.message
				}`,
			});
		}
	};

	const renderIngredients = formData.ingredients.map((ingredient) => (
		<AddIngredient
			key={ingredient.listId}
			ingredient={ingredient}
			updateIngredient={updateIngredient}
			removeIngredient={removeIngredient}
		/>
	));

	return (
		<>
			<div className='bg'>
				<Form
					onSubmit={handleSubmit}
					className='m-3'>
					<h1 className='h3 bg-dark text-bg-primary mt-2'>
						Add Recipe
					</h1>
					{submitStatus.show && (
						<Alert
							variant={
								submitStatus.success ? 'success' : 'danger'
							}
							dismissible
							onClose={() =>
								setSubmitStatus({
									...submitStatus,
									show: false,
								})
							}>
							{submitStatus.message}
						</Alert>
					)}
					<Form.Group
						className='mb-1'
						controlId='formBasicName'>
						<Form.Label>Recipe Name:</Form.Label>
						<Form.Control
							placeholder='Name'
							name='name'
							value={formData.name}
							onChange={handleInputChange}
							required
						/>
					</Form.Group>
					<Form.Group
						className='mb-1'
						controlId='formBasicDescription'>
						<Form.Label>Description:</Form.Label>
						<Form.Control
							placeholder='Description'
							name='description'
							value={formData.description}
							onChange={handleInputChange}
							as='textarea'
							rows={3}
						/>
					</Form.Group>
					<Form.Group
						className='mb-5'
						controlId='formBasicImageUrl'>
						<Form.Label>Image URL:</Form.Label>
						<Form.Control
							placeholder='URL'
							name='imageUrl'
							value={formData.imageUrl}
							onChange={handleInputChange}
						/>
					</Form.Group>
					<Row>
						<Col>Ingredient</Col>
						<Col>Unit</Col>
						<Col>Quanity</Col>
						<Col xs={1}></Col>
					</Row>
					<hr />
					<Row>
						<br></br>
					</Row>
					{renderIngredients}
					<Row>
						<br></br>
						<Button
							variant='warning'
							onClick={addIngredient}
							className='mt-1'
							type='button'>
							Add Ingredient
						</Button>
					</Row>
					<Button
						variant='primary'
						type='submit'
						className='mt-3 mb-5'>
						Submit Recipe
					</Button>
				</Form>
			</div>
		</>
	);
}

export default AddRecipe;

