import { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'motion/react';
import { Menu, X, Moon, Sun } from 'lucide-react';
import { NAV_LINKS } from '../constants';
import { cn } from '../lib/utils';

export const Navbar = () => {
  const [isScrolled, setIsScrolled] = useState(false);
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const [isDarkMode, setIsDarkMode] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 20);
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  useEffect(() => {
    if (isDarkMode) {
      document.documentElement.classList.add('dark');
    } else {
      document.documentElement.classList.remove('dark');
    }
  }, [isDarkMode]);

  const toggleDarkMode = () => setIsDarkMode(!isDarkMode);

  return (
    <nav
      className={cn(
        'fixed top-0 z-50 w-full transition-all duration-300',
        isScrolled
          ? 'bg-white/80 py-4 shadow-sm backdrop-blur-md dark:bg-zinc-950/80'
          : 'bg-transparent py-6'
      )}
    >
      <div className="mx-auto flex max-w-7xl items-center justify-between px-6">
        <a href="#home" className="text-xl font-bold tracking-tighter text-zinc-900 dark:text-white">
          PORTFOLIO<span className="text-zinc-400">.</span>
        </a>

        {/* Desktop Nav */}
        <div className="hidden items-center gap-8 md:flex">
          {NAV_LINKS.map((link) => (
            <a
              key={link.href}
              href={link.href}
              className="text-sm font-medium text-zinc-600 transition-colors hover:text-zinc-900 dark:text-zinc-400 dark:hover:text-white"
            >
              {link.label}
            </a>
          ))}
          <button
            onClick={toggleDarkMode}
            className="rounded-full p-2 text-zinc-600 hover:bg-zinc-100 dark:text-zinc-400 dark:hover:bg-zinc-800"
          >
            {isDarkMode ? <Sun size={20} /> : <Moon size={20} />}
          </button>
        </div>

        {/* Mobile Nav Toggle */}
        <div className="flex items-center gap-4 md:hidden">
          <button
            onClick={toggleDarkMode}
            className="rounded-full p-2 text-zinc-600 dark:text-zinc-400"
          >
            {isDarkMode ? <Sun size={20} /> : <Moon size={20} />}
          </button>
          <button
            onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)}
            className="text-zinc-900 dark:text-white"
          >
            {isMobileMenuOpen ? <X size={24} /> : <Menu size={24} />}
          </button>
        </div>
      </div>

      {/* Mobile Menu */}
      <AnimatePresence>
        {isMobileMenuOpen && (
          <motion.div
            initial={{ opacity: 0, y: -20 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -20 }}
            className="absolute top-full w-full bg-white px-6 py-8 shadow-lg dark:bg-zinc-900 md:hidden"
          >
            <div className="flex flex-col gap-6">
              {NAV_LINKS.map((link) => (
                <a
                  key={link.href}
                  href={link.href}
                  onClick={() => setIsMobileMenuOpen(false)}
                  className="text-lg font-medium text-zinc-600 dark:text-zinc-400"
                >
                  {link.label}
                </a>
              ))}
            </div>
          </motion.div>
        )}
      </AnimatePresence>
    </nav>
  );
};
