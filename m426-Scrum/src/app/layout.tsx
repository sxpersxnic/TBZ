import '@/ui/global.css'
import { inter } from 'src/ui/fonts';
import { Metadata } from 'next';

/**
 * Description placeholder
 *
 * @type {Metadata}
 */
export const metadata: Metadata = {
  title: {
    template: '%s | PH-Studio',
    default: 'Blog',
  },
  description: 'M426 - Blog',
};

/**
 * Description placeholder
 *
 * @export
 * @param {{
 *   children: React.ReactNode;
 * }} param0
 * @param {React.ReactNode} param0.children
 * @returns {*}
 */
export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className={`${inter.className} antialiased`}>{children}</body>
    </html>
  );
}
