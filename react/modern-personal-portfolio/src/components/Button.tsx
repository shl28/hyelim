import React from 'react';
import { motion } from 'motion/react';
import { cn } from '../lib/utils';

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: 'primary' | 'secondary' | 'outline' | 'ghost';
  size?: 'sm' | 'md' | 'lg';
  children: React.ReactNode;
}

export const Button = ({
  variant = 'primary',
  size = 'md',
  className,
  children,
  ...props
}: ButtonProps) => {
  const variants = {
    primary: 'bg-zinc-900 text-white hover:bg-zinc-800 dark:bg-white dark:text-zinc-900 dark:hover:bg-zinc-100',
    secondary: 'bg-zinc-100 text-zinc-900 hover:bg-zinc-200 dark:bg-zinc-800 dark:text-white dark:hover:bg-zinc-700',
    outline: 'border border-zinc-200 bg-transparent hover:bg-zinc-50 dark:border-zinc-700 dark:hover:bg-zinc-800',
    ghost: 'bg-transparent hover:bg-zinc-100 dark:hover:bg-zinc-800',
  };

  const sizes = {
    sm: 'px-3 py-1.5 text-sm',
    md: 'px-6 py-2.5',
    lg: 'px-8 py-3 text-lg',
  };

  return (
    <motion.button
      whileHover={{ scale: 1.02 }}
      whileTap={{ scale: 0.98 }}
      className={cn(
        'inline-flex items-center justify-center rounded-full font-medium transition-colors focus:outline-none focus:ring-2 focus:ring-zinc-400 focus:ring-offset-2 disabled:pointer-events-none disabled:opacity-50',
        variants[variant],
        sizes[size],
        className
      )}
      {...props}
    >
      {children}
    </motion.button>
  );
};
