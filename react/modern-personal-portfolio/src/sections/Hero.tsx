import { motion } from 'motion/react';
import { ArrowRight } from 'lucide-react';
import { Button } from '../components/Button';

export const Hero = () => {
  return (
    <section id="home" className="relative flex min-h-screen items-center justify-center overflow-hidden pt-20">
      {/* Background Accents */}
      <div className="absolute top-1/4 -left-20 h-64 w-64 rounded-full bg-zinc-100 blur-3xl dark:bg-zinc-800/20" />
      <div className="absolute bottom-1/4 -right-20 h-96 w-96 rounded-full bg-zinc-100 blur-3xl dark:bg-zinc-800/20" />

      <div className="relative z-10 mx-auto max-w-4xl px-6 text-center">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.6 }}
        >
          <span className="inline-block rounded-full bg-zinc-100 px-4 py-1.5 text-sm font-medium text-zinc-600 dark:bg-zinc-800 dark:text-zinc-400">
            Available for new projects
          </span>
          <h1 className="mt-8 text-5xl font-bold tracking-tight text-zinc-900 sm:text-7xl dark:text-white">
            Building digital products that <span className="text-zinc-400">matter.</span>
          </h1>
          <p className="mx-auto mt-8 max-w-2xl text-lg text-zinc-600 dark:text-zinc-400 sm:text-xl">
            I'm a Full Stack Developer and UI Designer focused on creating clean, 
            performant, and user-centric web experiences.
          </p>
          <div className="mt-12 flex flex-col items-center justify-center gap-4 sm:flex-row">
            <Button size="lg" className="gap-2">
              View Portfolio <ArrowRight size={20} />
            </Button>
            <Button variant="outline" size="lg">
              Contact Me
            </Button>
          </div>
        </motion.div>
      </div>

      <motion.div
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        transition={{ delay: 1, duration: 1 }}
        className="absolute bottom-10 left-1/2 -translate-x-1/2"
      >
        <div className="h-12 w-6 rounded-full border-2 border-zinc-200 dark:border-zinc-800">
          <motion.div
            animate={{ y: [0, 24, 0] }}
            transition={{ repeat: Infinity, duration: 1.5 }}
            className="mx-auto mt-2 h-2 w-1 rounded-full bg-zinc-400"
          />
        </div>
      </motion.div>
    </section>
  );
};
