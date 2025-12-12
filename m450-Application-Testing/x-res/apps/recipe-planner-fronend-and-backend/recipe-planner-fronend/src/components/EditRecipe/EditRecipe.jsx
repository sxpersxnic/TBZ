import { useEffect, useState } from 'react';
import { Alert, Button, Col, Form, Row, Spinner } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';
import './EditRecipe.css';

import axios from 'axios';
import AddIngredient from '../AddIngredient/AddIngredient';

const baseURL = 'http://localhost:8080/api/recipes';

function EditRecipe() {
	const { recipeId } = useParams();
	const navigate = useNavigate();
	const [loading, setLoading] = useState(true);
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

	// Fetch the recipe data when component mounts
	useEffect(() => {
		const fetchRecipe = async () => {
			try {
				const response = await axios.get(
					`${baseURL}/recipe/${recipeId}`
				);
				const recipe = response.data;

				// Add listId to each ingredient for tracking
				const ingredientsWithListId =
					recipe.ingredients?.map((ing, index) => ({
						...ing,
						listId: index + 1,
					})) || [];

				setFormData({
					id: recipe.id,
					name: recipe.name || '',
					description: recipe.description || '',
					imageUrl: recipe.imageUrl || '',
					ingredients: ingredientsWithListId,
				});
				setListId(ingredientsWithListId.length + 1);
				setLoading(false);
			} catch (error) {
				setSubmitStatus({
					show: true,
					success: false,
					message: `Failed to load recipe: ${
						error.response?.data?.message || error.message
					}`,
				});
				setLoading(false);
			}
		};

		if (recipeId) {
			fetchRecipe();
		}
	}, [recipeId]);

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
			id: formData.id,
			name: formData.name,
			description: formData.description,
			imageUrl: formData.imageUrl,
			ingredients: formData.ingredients.map((ing) => ({
				id: ing.id || null,
				name: ing.name,
				unit: ing.unit,
				amount: parseInt(ing.amount) || 0,
				comment: ing.comment || '',
			})),
		};

		try {
			await axios.put(baseURL, recipeData);
			setSubmitStatus({
				show: true,
				success: true,
				message: 'Recipe updated successfully!',
			});
		} catch (error) {
			setSubmitStatus({
				show: true,
				success: false,
				message: `Failed to update recipe: ${
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

	if (loading) {
		return (
			<div
				className='d-flex justify-content-center align-items-center'
				style={{ minHeight: '200px' }}>
				<Spinner
					animation='border'
					role='status'>
					<span className='visually-hidden'>Loading...</span>
				</Spinner>
			</div>
		);
	}

	return (
		<>
			<div className='bg'>
				<Form
					onSubmit={handleSubmit}
					className='m-3'>
					<h1 className='h3 bg-dark text-bg-primary mt-2'>
						Edit Recipe
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
						<Col>Amount</Col>
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
					<div className='d-flex gap-2 mt-3 mb-5'>
						<Button
							variant='primary'
							type='submit'>
							Update Recipe
						</Button>
						<Button
							variant='secondary'
							type='button'
							onClick={() => navigate('/')}>
							Cancel
						</Button>
					</div>
				</Form>
			</div>
		</>
	);
}

export default EditRecipe;
