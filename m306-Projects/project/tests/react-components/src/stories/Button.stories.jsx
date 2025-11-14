import { Button } from '../components/button';

export default {
	title: 'Components/Button',
	component: Button,
	parameters: {
		layout: 'centered',
	},
	tags: ['autodocs'],
	argTypes: {
		backgroundColor: { control: 'color' },
		size: {
			control: { type: 'select' },
			options: ['small', 'medium', 'large'],
		},
		primary: { control: 'boolean' },
		onClick: { action: 'clicked' },
	},
};

export const Primary = {
	args: {
		primary: true,
		label: 'Primary Button',
	},
};

export const Secondary = {
	args: {
		primary: false,
		label: 'Secondary Button',
	},
};

export const Small = {
	args: {
		size: 'small',
		label: 'Small Button',
	},
};

export const Medium = {
	args: {
		size: 'medium',
		label: 'Medium Button',
	},
};

export const Large = {
	args: {
		size: 'large',
		label: 'Large Button',
	},
};

export const CustomBackgroundColor = {
	args: {
		backgroundColor: '#ff6b6b',
		label: 'Custom Color Button',
	},
};

export const PrimarySmall = {
	args: {
		primary: true,
		size: 'small',
		label: 'Primary Small',
	},
};

export const PrimaryLarge = {
	args: {
		primary: true,
		size: 'large',
		label: 'Primary Large',
	},
};

