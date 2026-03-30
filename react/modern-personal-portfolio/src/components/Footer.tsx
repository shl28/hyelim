import { SOCIAL_LINKS } from '../constants';

export const Footer = () => {
  return (
    <footer className="border-t border-zinc-200 py-12 dark:border-zinc-800">
      <div className="mx-auto max-w-7xl px-6">
        <div className="flex flex-col items-center justify-between gap-6 md:flex-row">
          <p className="text-sm text-zinc-500 dark:text-zinc-400">
            © {new Date().getFullYear()} Portfolio. Built with React & Tailwind.
          </p>
          <div className="flex gap-6">
            {SOCIAL_LINKS.map((social) => (
              <a
                key={social.label}
                href={social.href}
                className="text-sm text-zinc-500 transition-colors hover:text-zinc-900 dark:text-zinc-400 dark:hover:text-white"
              >
                {social.label}
              </a>
            ))}
          </div>
        </div>
      </div>
    </footer>
  );
};
