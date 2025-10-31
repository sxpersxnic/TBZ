import { Header } from '../components/header';

export default {
	title: 'Components/Header',
	component: Header,
	parameters: {
		layout: 'fullscreen',
	},
	tags: ['autodocs'],
	argTypes: {
		onLogin: { action: 'onLogin' },
		onLogout: { action: 'onLogout' },
		onCreateAccount: { action: 'onCreateAccount' },
	},
};

export const LoggedOut = {
	args: {
		user: null,
	},
};

export const LoggedIn = {
	args: {
		user: {
			name: 'John Doe',
		},
	},
};

export const LongUsername = {
	args: {
		user: {
			name: 'Christopher Alexander Montgomery',
		},
	},
};

export const ShortUsername = {
	args: {
		user: {
			name: 'Jo',
		},
	},
};

