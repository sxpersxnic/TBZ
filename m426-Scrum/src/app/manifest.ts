import { MetadataRoute } from "next";

/**
 * Description placeholder
 *
 * @export
 * @returns {MetadataRoute.Manifest}
 */
export default function manifest(): MetadataRoute.Manifest {
  return {
    name: 'PH-Studio Blog',
    short_name: 'Blog',
    description: 'PH-Studio Blog for M426',
    start_url: '/',
    display: 'standalone',    
    background_color: '#fff',
    theme_color: '#000',
    icons: [
      {
        src: '/favicon.ico',
        sizes: '24x24',
        type: 'image/x-icon',
      },
      {
        src: '/favicon.svg',
        sizes: 'any',
        type: 'image/svg'
      }
    ],
  }
}